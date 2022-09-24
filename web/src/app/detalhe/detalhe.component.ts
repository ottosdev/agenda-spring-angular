import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Contato } from '../contato/contato';
@Component({
  selector: 'app-detalhe',
  templateUrl: './detalhe.component.html',
  styleUrls: ['./detalhe.component.css'],
})
export class DetalheComponent implements OnInit {
  constructor(
    public ref: MatDialogRef<DetalheComponent>,
    @Inject(MAT_DIALOG_DATA) public contato: Contato
  ) {}

  ngOnInit(): void {}

  fechar() {
    this.ref.close();
  }
}
