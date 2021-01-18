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
import com.pdi.projetopdi.ui.logic.DialogEditarProdutoPedidoLogic;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DialogEditarProdutoPedido extends DialogFragment {

    private EditText produtoDigitadoEditText;
    private Button btBuscarProdutoDescricao;
    private TextView precoOriginalBanco;

    private EditText quantidadeProdutoDigitadoEditText;
    private  EditText precoVendaDigitadoEditText;
    private  EditText descontoProdutoDigitadoEditText;
    private  TextView totalProdutoTextView;
    private Button btAddProduto;

    private BigDecimal quantidadeBigDecimal;
    private BigDecimal precoVendaBigDecimal;
    private BigDecimal descontoBigDecimal;
//    private BigDecimal precoBigDecimal;
    private BigDecimal totalProdutoBigDecimal;

    private ProdutoRepository prdDao;
    private Produto produtoObj;
    private PedidoItem pedidoItem;

    private DialogEditarProdutoPedidoLogic viewModel;

    public DialogEditarProdutoPedido(PedidoItem item){
        this.pedidoItem = item;
    }

    public static DialogEditarProdutoPedido newInstance(PedidoItem item){
        return new DialogEditarProdutoPedido(item);
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
        View view = inflater.inflate(R.layout.dialog_add_editar_produto_pedido, container, false);

        //Pegar valor dos campos po id
        produtoDigitadoEditText = view.findViewById(R.id.digitarProduto);
        btBuscarProdutoDescricao = view.findViewById(R.id.btBuscarDescricao);
        precoOriginalBanco = view.findViewById(R.id.idPrecoProduto);
        quantidadeProdutoDigitadoEditText = view.findViewById(R.id.idQuantidade);
        precoVendaDigitadoEditText = view.findViewById(R.id.idPrecoVenda);
        descontoProdutoDigitadoEditText = view.findViewById(R.id.idDescontoProduto);
        totalProdutoTextView =view.findViewById(R.id.idValorTotalProduto);
        btAddProduto = view.findViewById(R.id.idBtAddNovoPrd);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(pedidoItem==null){

        }

        //conexao com banco
        prdDao = new ProdutoRepository(getActivity());

        viewModel = new DialogEditarProdutoPedidoLogic(getActivity());

         clicouBotaoBuscarProduto();

        quantidadeBigDecimal = new BigDecimal(BigInteger.ZERO);
//        precoBigDecimal = new BigDecimal(BigInteger.ZERO);
        precoVendaBigDecimal = new BigDecimal(BigInteger.ZERO);
        descontoBigDecimal = new BigDecimal(BigInteger.ZERO);
        precoVendaBigDecimal = new BigDecimal(BigInteger.ZERO);
        totalProdutoBigDecimal = new BigDecimal(BigInteger.ZERO);

        viewModel.setPrecoVendaBigDecimal(precoVendaBigDecimal);
        viewModel.setDescontoBigDecimal(descontoBigDecimal);
        verificaFocoEditText();
        clicouBotaoAddProduto();
    }

    private void verificaFocoEditText() {
        quantidadeProdutoDigitadoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.calcularValoresAlterandoQntd();
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
                    viewModel.calcularValoresAlterandoPrecoVenda(precoVendaBigDecimal);
                    apresentarValores();
                }
            }
        });

        descontoProdutoDigitadoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    descontoBigDecimal = new BigDecimal(descontoProdutoDigitadoEditText.getText().toString());
                    viewModel.calcularValoresAlterandoDesc(descontoBigDecimal);
                    apresentarValores();
                }
            }
        });
    }

    public void apresentarValores(){
        precoVendaDigitadoEditText.setText(String.valueOf(viewModel.getPrecoVendaBigDecimal()));
        descontoProdutoDigitadoEditText.setText(String.valueOf(viewModel.getDescontoBigDecimal()));
        totalProdutoTextView.setText(String.valueOf(viewModel.getPrecoVendaBigDecimal().multiply(quantidadeBigDecimal)));
    }

    private void clicouBotaoBuscarProduto(){
        btBuscarProdutoDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produtoObj = viewModel.buscaProduto(produtoDigitadoEditText.getText().toString());
                if(!(produtoObj == null)) {
                    produtoDigitadoEditText.setText(String.valueOf(produtoObj.getDescricao()));
                    precoOriginalBanco.setText(String.valueOf(produtoObj.getPreco()));
//                    precoBigDecimal = produtoObj.getPreco();
                    totalProdutoTextView.setText(String.valueOf(produtoObj.getPreco()));
                }else{
                    Log.i("teste botao add", "entrou no if");
                }
            }
        });
    }

    private void clicouBotaoAddProduto() {
        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( totalProdutoTextView.getText().toString().isEmpty() /*|| totalProdutoBigDecimal.doubleValue() <= 0*/){
                    Toast.makeText(getActivity(),"Valor total negativo! Não é possível efetuar adicionar o produto.", Toast.LENGTH_SHORT);
                }else{
                    pedidoItem =viewModel.salvarProduto(quantidadeBigDecimal);
                    ((NovoPedidoActivity)getActivity()).pedidoItemList.add(pedidoItem);
                    dismiss();
                }
            }
        });
    }

}
