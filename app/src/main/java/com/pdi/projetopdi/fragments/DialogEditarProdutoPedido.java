package com.pdi.projetopdi.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.pdi.projetopdi.dao.ProdutoDAO;
import com.pdi.projetopdi.modelo.PedidoItem;
import com.pdi.projetopdi.modelo.Produto;
import com.pdi.projetopdi.ui.activity.NovoPedidoTeste;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private BigDecimal precoBigDecimal;
    private BigDecimal totalProdutoBigDecimal;

    private ProdutoDAO prdDao;
    private Produto produtoObj;
    private PedidoItem pedidoItem;

    View.OnFocusChangeListener focusListener;

    public static DialogEditarProdutoPedido newInstance(){
        return new DialogEditarProdutoPedido();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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



        //conexao com banco
        prdDao = new ProdutoDAO(getActivity());

        clicouBotaoBuscarProduto();
        onFocusEditText();

        if(quantidadeProdutoDigitadoEditText.getText().toString().isEmpty()) {
            quantidadeProdutoDigitadoEditText.setOnFocusChangeListener(focusListener);
            precoVendaDigitadoEditText.setOnFocusChangeListener(focusListener);
            descontoProdutoDigitadoEditText.setOnFocusChangeListener(focusListener);
        }

        clicouBotaoAddProduto();



        return view;
    }

    private void onFocusEditText(){
        focusListener = new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                try {

                    //transformando os valores digitados em BigDecimal
                    quantidadeBigDecimal = new BigDecimal(quantidadeProdutoDigitadoEditText.getText().toString());
                    precoBigDecimal = new BigDecimal(precoOriginalBanco.getText().toString());
                    precoVendaBigDecimal = new BigDecimal(precoOriginalBanco.getText().toString());
                    descontoBigDecimal = new BigDecimal(descontoProdutoDigitadoEditText.getText().toString());
                    precoVendaBigDecimal = new BigDecimal(precoVendaDigitadoEditText.getText().toString());
                    totalProdutoBigDecimal = new BigDecimal(totalProdutoTextView.getText().toString());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.i("Continue", "Deu nullPointer :(");
                } catch (NumberFormatException nF) {
                    nF.printStackTrace();
                }


                    if (precoBigDecimal != null && descontoBigDecimal != null)
                        precoVendaDigitadoEditText.setText(String.valueOf(precoBigDecimal.subtract(descontoBigDecimal).setScale(2, RoundingMode.HALF_EVEN)));
//                        precoVendaDigitadoEditText.setText(String.valueOf(precoBigDecimal.doubleValue() - descontoBigDecimal.doubleValue()));


                    if (precoBigDecimal != null && precoVendaBigDecimal != null)
                        descontoProdutoDigitadoEditText.setText(String.valueOf(precoBigDecimal.subtract(precoVendaBigDecimal).setScale(2, RoundingMode.HALF_EVEN)));
//                        descontoProdutoDigitadoEditText.setText(String.valueOf(precoBigDecimal.doubleValue() - precoVendaBigDecimal.doubleValue()));

                    if (precoVendaBigDecimal != null && quantidadeBigDecimal != null)
                        totalProdutoTextView.setText(String.valueOf(precoVendaBigDecimal.multiply(quantidadeBigDecimal)));


            }
        };
    }

    public void calcularValores(){
        precoVendaDigitadoEditText.setText(String.valueOf(precoBigDecimal.subtract(descontoBigDecimal).setScale(2, RoundingMode.HALF_EVEN)));
        descontoProdutoDigitadoEditText.setText(String.valueOf(precoBigDecimal.subtract(precoVendaBigDecimal).setScale(2, RoundingMode.HALF_EVEN)));
        totalProdutoTextView.setText(String.valueOf(precoVendaBigDecimal.multiply(quantidadeBigDecimal)));
    }

    private void clicouBotaoBuscarProduto(){
        btBuscarProdutoDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produtoObj = prdDao.buscaProdutoDesc(produtoDigitadoEditText.getText().toString());
                if(!(produtoObj == null)) {
                    produtoDigitadoEditText.setText(String.valueOf(produtoObj.getDescricao()));
                    precoOriginalBanco.setText(String.valueOf(produtoObj.getPreco()));
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
                    pedidoItem = new PedidoItem(produtoObj.getIdproduto(),quantidadeBigDecimal.intValue(),precoBigDecimal,precoVendaBigDecimal,descontoBigDecimal);
                    Intent i = new Intent().putExtra("teste","oi");
                    Intent it = new Intent(getContext(), NovoPedidoTeste.class);
                    it.putExtra("obj", pedidoItem);  // verificar para passar somente o id mais fácil (pesquisar)
                    getActivity().startActivity(it);
//                    dismiss();
                }
            }
        });
    }

}
