package com.pdi.projetopdi.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pdi.projetopdi.R;
import com.pdi.projetopdi.repository.ProdutoRepository;
import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.ui.activity.NovoPedidoActivity;
import com.pdi.projetopdi.logic.DialogEditarProdutoPedidoLogic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class DialogEditarProdutoPedido extends DialogFragment {

    private EditText produtoDigitadoEditText;
    private Button buscarProdutoDescricaoButton;
    private TextView precoOriginalBancoTextView;

    private EditText quantidadeProdutoDigitadoEditText;
    private EditText precoVendaDigitadoEditText;
    private EditText descontoProdutoDigitadoEditText;
    private TextView totalProdutoTextView;
    private Button addProdutoButton;

    private BigDecimal precoOriginalBigDecimal;
    private BigDecimal quantidadeBigDecimal;
    private BigDecimal precoVendaBigDecimal;
    private BigDecimal descontoBigDecimal;
    private BigDecimal totalProdutoBigDecimal;


    private Produto produtoNovo;
    private PedidoItem itemRecebidoEditar;

    private DialogEditarProdutoPedidoLogic dialogLogic;

    public DialogEditarProdutoPedido(PedidoItem item) {
        this.itemRecebidoEditar = item;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_add_editar_produto_pedido,
                container, false);

        //Pegar valor dos campos po id
        produtoDigitadoEditText = view.findViewById(R.id.digitarProduto);
        buscarProdutoDescricaoButton = view.findViewById(R.id.btBuscarDescricao);
        precoOriginalBancoTextView = view.findViewById(R.id.idPrecoProduto);
        quantidadeProdutoDigitadoEditText = view.findViewById(R.id.idQuantidade);
        precoVendaDigitadoEditText = view.findViewById(R.id.idPrecoVenda);
        descontoProdutoDigitadoEditText = view.findViewById(R.id.idDescontoProduto);
        totalProdutoTextView = view.findViewById(R.id.idValorTotalProduto);
        addProdutoButton = view.findViewById(R.id.idBtAddNovoPrd);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        dialogLogic = new DialogEditarProdutoPedidoLogic(getActivity());

        if (itemRecebidoEditar != null) {
            SetandoValoresQuandoEditaPeditoItem();
        } else {

            quantidadeBigDecimal = new BigDecimal(BigInteger.ZERO);
            precoOriginalBigDecimal = new BigDecimal(BigInteger.ZERO);
            precoVendaBigDecimal = new BigDecimal(BigInteger.ZERO);
            descontoBigDecimal = new BigDecimal(BigInteger.ZERO);
            precoVendaBigDecimal = new BigDecimal(BigInteger.ZERO);
            totalProdutoBigDecimal = new BigDecimal(BigInteger.ZERO);
        }

        dialogLogic.setPrecoVendaBigDecimal(precoVendaBigDecimal);
        dialogLogic.setDescontoBigDecimal(descontoBigDecimal);
        apresentarValores();

        clicouBotaoBuscarProduto();
        verificaFocoEditText();
        clicouBotaoAddProduto();
    }

    private void SetandoValoresQuandoEditaPeditoItem() {
        dialogLogic.buscaProduto(itemRecebidoEditar.getIdProduto());
        produtoNovo =dialogLogic.getProdutoNovo();

        produtoDigitadoEditText.setText(produtoNovo.getDescricao());
        quantidadeBigDecimal = new BigDecimal(itemRecebidoEditar.getQuantidade());
        quantidadeProdutoDigitadoEditText.setText(String.valueOf(quantidadeBigDecimal));
        precoVendaBigDecimal = itemRecebidoEditar.getPrecoVenda();
        descontoBigDecimal = itemRecebidoEditar.getValorDesconto();

        //setando valores recebidos
        precoOriginalBancoTextView.setText(String.valueOf(itemRecebidoEditar.getPrecoOriginal()
                .divide(new BigDecimal(100))));
        totalProdutoBigDecimal = produtoNovo.getPreco().multiply(quantidadeBigDecimal)
                .divide(new BigDecimal(100));

        dialogLogic.setPrecoOriginalBigDecimal(itemRecebidoEditar.getPrecoOriginal());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void verificaFocoEditText() {
        quantidadeProdutoDigitadoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    dialogLogic.calcularValoresAlterandoQntd();
                    quantidadeBigDecimal = new BigDecimal(quantidadeProdutoDigitadoEditText.getText().toString());
                    apresentarValores();
                }
            }
        });

        precoVendaDigitadoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    precoVendaBigDecimal = new BigDecimal(precoVendaDigitadoEditText.getText().toString());
                    dialogLogic.calcularValoresAlterandoPrecoVenda(precoVendaBigDecimal);
                    apresentarValores();
                }
            }
        });

        descontoProdutoDigitadoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    descontoBigDecimal = new BigDecimal(descontoProdutoDigitadoEditText.getText().toString());
                    dialogLogic.calcularValoresAlterandoDesc(descontoBigDecimal);
                    apresentarValores();
                }
            }
        });
    }

    public void apresentarValores() {
        precoVendaDigitadoEditText.setText(String.valueOf(dialogLogic.getPrecoVendaBigDecimal().setScale(2, RoundingMode.HALF_EVEN)));
        descontoProdutoDigitadoEditText.setText(String.valueOf(dialogLogic.getDescontoBigDecimal().setScale(2, RoundingMode.HALF_EVEN)));
        totalProdutoTextView.setText(String.valueOf(dialogLogic.getPrecoVendaBigDecimal().multiply(quantidadeBigDecimal).setScale(2, RoundingMode.HALF_EVEN)));
    }

    private void clicouBotaoBuscarProduto() {
        buscarProdutoDescricaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLogic.buscaProduto(produtoDigitadoEditText.getText().toString());
                produtoNovo = dialogLogic.getProdutoNovo();
                if (!(produtoNovo== null)) {
                    produtoDigitadoEditText.setText(String.valueOf(produtoNovo.getDescricao()));
                    precoOriginalBancoTextView.setText(String.valueOf(produtoNovo.getPreco()));
                    totalProdutoTextView.setText(String.valueOf(produtoNovo.getPreco()));
                } else {
                    Toast.makeText(getContext(), "Produto não encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clicouBotaoAddProduto() {
        addProdutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalProdutoTextView.getText().toString().isEmpty()
                        || totalProdutoTextView.getText().toString() == "0") {

                    Toast.makeText(getActivity(),
                            "Valor incorreto! Não é possível adicionar o produto.",
                            Toast.LENGTH_SHORT);
                } else {
                    try{
                    itemRecebidoEditar = dialogLogic.salvarProduto(quantidadeBigDecimal);
                    ((NovoPedidoActivity) getActivity()).pedidoItemList.add(itemRecebidoEditar);

                    }catch (NullPointerException nullPointer){
                        nullPointer.printStackTrace();
                        Toast.makeText(getContext(), "Verifique as informações preenchidas!",
                                Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                }
            }
        });
    }

}
