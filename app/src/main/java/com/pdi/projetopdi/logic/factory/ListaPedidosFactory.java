package com.pdi.projetopdi.logic.factory;

import com.pdi.projetopdi.repository.PedidoRepository;

public class ListaPedidosFactory {

        private PedidoRepository repository;

        public ListaPedidosFactory(PedidoRepository repository){
            this.repository = repository;
        }

//        @NonNull
//        @Override
//        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//            return new ListaPedidosLogic(repository);
//        }

}
