package com.pdi.projetopdi.ui.logic;

import android.content.Context;
import android.util.Log;

import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DialogEditarProdutoPedidoLogic {

    private BigDecimal quantidadeBigDecimal;


    private BigDecimal precoVendaBigDecimal;
    private BigDecimal descontoBigDecimal;
    private BigDecimal precoBigDecimal;
    private BigDecimal totalProdutoBigDecimal;

    private ProdutoRepository prdDao;
    private Produto produtoObj;
    private PedidoItem pedidoItem;

    private Context context;

    public DialogEditarProdutoPedidoLogic(Context context){
        this.prdDao = ProdutoRepository.getInstance(context);
    }

    public BigDecimal getPrecoVendaBigDecimal() {
        return precoVendaBigDecimal;
    }

    public void setPrecoVendaBigDecimal(BigDecimal precoVendaBigDecimal) {
        this.precoVendaBigDecimal = precoVendaBigDecimal;
    }

    public BigDecimal getDescontoBigDecimal() {
        return descontoBigDecimal;
    }

    public void setDescontoBigDecimal(BigDecimal descontoBigDecimal) {
        this.descontoBigDecimal = descontoBigDecimal;
    }


    public void calcularValoresAlterandoQntd(){
        this.precoVendaBigDecimal =precoBigDecimal.subtract(descontoBigDecimal).setScale(2, RoundingMode.HALF_EVEN);
        this.descontoBigDecimal =precoBigDecimal.subtract(precoVendaBigDecimal).setScale(2, RoundingMode.HALF_EVEN);
        setPrecoVendaBigDecimal(precoVendaBigDecimal);
        setDescontoBigDecimal(descontoBigDecimal);
    }

    public void calcularValoresAlterandoPrecoVenda(BigDecimal precoVendaBigDecimal){
        descontoBigDecimal =precoBigDecimal.subtract(precoVendaBigDecimal).setScale(2, RoundingMode.HALF_EVEN);
        setPrecoVendaBigDecimal(precoVendaBigDecimal);
        setDescontoBigDecimal(descontoBigDecimal);
    }

    public void calcularValoresAlterandoDesc(BigDecimal descontoBigDecimal){
//        descontoBigDecimal = new BigDecimal(descontoProdutoDigitadoEditText.getText().toString());
        precoVendaBigDecimal =precoBigDecimal.subtract(descontoBigDecimal)
                .setScale(2, RoundingMode.HALF_EVEN);
        setPrecoVendaBigDecimal(precoVendaBigDecimal);
        setDescontoBigDecimal(descontoBigDecimal);
    }


    public Produto buscaProduto(String produtoDigitado){
        produtoObj = prdDao.buscaProdutoDesc(produtoDigitado);
        if(!(produtoObj == null)) {
            precoBigDecimal = produtoObj.getPreco();
            return produtoObj;
        }else{
            Log.i("teste botao add", "entrou no if");
        }
        return null;
    }

    public PedidoItem salvarProduto(BigDecimal quantidadeBigDecimal){
        pedidoItem = new PedidoItem(produtoObj.getIdproduto(),
                quantidadeBigDecimal.intValue(),precoBigDecimal,
                precoVendaBigDecimal,descontoBigDecimal);
        return pedidoItem;
    }
}
