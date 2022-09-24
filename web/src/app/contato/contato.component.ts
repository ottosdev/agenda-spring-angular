import { Component, OnInit, DoCheck } from '@angular/core';
import { ContatoService } from '../contato.service';
import { Contato } from './contato';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DetalheComponent } from '../detalhe/detalhe.component';

interface IContatos {
  nome: string;
  email: string;
}
@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css'],
})
export class ContatoComponent implements OnInit {
  formulario!: FormGroup;
  contatos!: Contato[];
  colunas: String[] = ['foto', 'id', 'nome', 'email', 'favorito'];

  constructor(
    private service: ContatoService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.montarFormulario();
    this.listarContatos();
  }

  montarFormulario() {
    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.email, Validators.required]],
    });
  }

  submit() {
    const formValues = this.formulario.value as Contato;
    const contato = new Contato(formValues.nome, formValues.email);
    this.service.save(contato).subscribe((res) => {
      let lista: Contato[] = [...this.contatos, res];
      this.contatos = lista;
    });
  }

  favoritar(contato: Contato) {
    this.service.favourite(contato).subscribe((res) => {
      contato.favorito = !contato.favorito;
    });
  }

  listarContatos() {
    this.service.list().subscribe(
      (res) => {
        this.contatos = res;
      },
      (er) => {
        console.log('Falha ao retornar lista de contatos');
      }
    );
  }

  uploadFoto(event: any, contato: Contato) {
    const files = event.target.files;
    if (files) {
      const foto = files[0];
      const formData: FormData = new FormData();

      formData.append('foto', foto);
      this.service.upload(contato, formData).subscribe((res) => {
        this.listarContatos();
      });
    }
  }

  visualizarContato(contato:Contato) {
    this.dialog.open(DetalheComponent, {
      width: "1200px",
      height: "1200pxpx",
      data: contato
    })
  }
}
