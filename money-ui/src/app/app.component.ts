import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  lancamentos = [
    {tipo: 'DESPESA', descricao: 'compra de pão', dataVencimento: '30/06/2017',
    dataPagamento: '09/06/2017', valor: 4.55, pessoa: 'Atacado Brasil'}
  ];

}
