import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css']
})
export class PessoasPesquisaComponent  {

pessoas = [
  {nome: 'Leonardo', cidade: 'Brasília', estado: 'DF', ativo: true},
  {nome: 'Lorena Leticia', cidade: 'Brasília', estado: 'DF', ativo: true},
  {nome: 'Ana Abrahão', cidade: 'Brasília', estado: 'DF', ativo: false}
];

}
