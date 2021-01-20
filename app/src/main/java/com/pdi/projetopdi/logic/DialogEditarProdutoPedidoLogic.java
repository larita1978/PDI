package com.pdi.projetopdi.logic;

import android.content.Context;
import android.util.Log;

import com.pdi.projetopdi.model.PedidoItem;
import com.pdi.projetopdi.model.Produto;
import com.pdi.projetopdi.repository.PedidoItemRepository;
import com.pdi.projetopdi.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DialogEditarProdutoPedidoLogic {

    private BigDecimal precoOriginalBigDecimal;
    private BigDecimal precoVendaBigDecimal;
    private BigDecimal descontoBigDecimal;

    private ProdutoRepository produtoRepository;
    private Produto produtoNovo;
    private PedidoItem pedidoItem;

    private Context context;

    public DialogEditarProdutoPedidoLogic(Context context){
        this.context = context;
        this.produtoRepository = ProdutoRepository.getInstance(context);
    }
    public BigDecimal getPrecoVendaBigDecimal() {
        return precoVendaBigDecimal;
    }

    public void setPrecoVendaBigDecimal(BigDecimal precoVendaBigDecimal) {
        this.precoVendaBigDecimal =precoVendaBigDecimal;
    }

    public BigDecimal getDescontoBigDecimal() {
        return descontoBigDecimal;
    }

    public void setDescontoBigDecimal(BigDecimal descontoBigDecimal) {
        this.descontoBigDecimal = descontoBigDecimal;
    }

    public void setPrecoOriginalBigDecimal(BigDecimal precoOriginalBigDecimal) {
        this.precoOriginalBigDecimal = precoOriginalBigDecimal;
    }

    public Produto getProdutoNovo() {
        return produtoNovo;
    }

    public void setProdutoNovo(Produto produtoNovo) {
        this.produtoNovo = produtoNovo;
    }

    public void buscaProduto(long idProduto){
        setProdutoNovo(produtoRepository.buscaProdutoPorID(idProduto));
    }

    public void calcularValoresAlterandoQntd(){
        setPrecoVendaBigDecimal(precoOriginalBigDecimal.subtract(descontoBigDecimal));
        setDescontoBigDecimal(precoOriginalBigDecimal.subtract(precoVendaBigDecimal));
    }

    public void calcularValoresAlterandoPrecoVenda(BigDecimal precoVendaBigDecimal){
        setDescontoBigDecimal(precoOriginalBigDecimal.subtract(precoVendaBigDecimal));
        setPrecoVendaBigDecimal(precoVendaBigDecimal);
    }

    public void calcularValoresAlterandoDesc(BigDecimal descontoBigDecimal){
        setPrecoVendaBigDecimal(precoOriginalBigDecimal.subtract(descontoBigDecimal));
        setDescontoBigDecimal(descontoBigDecimal);
    }

    public void buscaProduto(String produtoDigitado){
        produtoNovo = produtoRepository.buscaProdutoDescricaoAdd(produtoDigitado);
        if(!(produtoNovo == null)) {
            precoOriginalBigDecimal = produtoNovo.getPreco();
            setProdutoNovo(produtoNovo);
        }else{
            Log.i("teste botao add", "entrou no if");
        }
    }

    public PedidoItem salvarProduto(BigDecimal quantidadeBigDecimal){

        try {
            pedidoItem = new PedidoItem(this.produtoNovo.getIdproduto(),
                    quantidadeBigDecimal.intValue(), precoOriginalBigDecimal.setScale(2, RoundingMode.HALF_EVEN),
                    precoVendaBigDecimal.setScale(2, RoundingMode.HALF_EVEN),
                    descontoBigDecimal.setScale(2, RoundingMode.HALF_EVEN));
        }catch (NullPointerException nullpointer){
            nullpointer.printStackTrace();
            return null;
        }

        if(this.produtoNovo == null){
            new PedidoItemRepository(context).atualiza(pedidoItem);
        }

        return pedidoItem;
    }
}
