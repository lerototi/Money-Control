import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})
export class LancamentosPesquisaComponent {

  lancamentos = [
    {tipo: 'DESPESA', descricao: 'compra de pão', dataVencimento: '30/06/2017',
    dataPagamento: '09/06/2017', valor: 4.55, pessoa: 'Atacado Brasil'}
  ];

}
