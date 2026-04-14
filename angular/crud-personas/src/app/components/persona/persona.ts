import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Persona } from '../../models/persona';
import { PersonaService } from '../../service/persona';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-persona',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template:'<router-outlet></router-outlet>',
  templateUrl: './persona.html',
  styleUrl: './persona.css',
})
export class PersonaComponent implements OnInit {
  personas: Persona[] = [];
  persona: Persona={
    id: 0,
    nombre: '',
  };
  constructor(private personaService: PersonaService, private cdr: ChangeDetectorRef ) {}
  ngOnInit(): void {
    console.log('componente.persona.ngOnInit');
    this.cargarPersonasComponent();
  }

  cargarPersonasComponent(){

    this.personaService.getPersonasHttp().subscribe(data => {
      this.personas = data;
      this.cdr.detectChanges();


    });
    error: (err: any) => console.error(err)  
  }

  guardar(){
    if(this.persona.id===0){
      this.personaService.crearPersona(this.persona).subscribe(() => {
        this.cargarPersonasComponent();
        this.limpiar();
      });
    }
    else{
      this.personaService.actualizarPersona(this.persona.id,this.persona).subscribe(() => {
        this.cargarPersonasComponent();
        this.limpiar();
      });
    }
  }

  eliminar(id: number){
    this.personaService.eliminarPersona(id).subscribe(() =>{
      this.cargarPersonasComponent();

    });
  }

  editar(persona: Persona){
    this.persona = {...persona};
  }

  limpiar(){
    this.persona ={id: 0, nombre: ''};
  }

  
}
