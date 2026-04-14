import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Persona } from '../models/persona';

@Injectable({
  providedIn: 'root',
})
export class PersonaService {
  private apiUrl="http://localhost:8080/api/v1/entities/personas";
  constructor(private http: HttpClient) {}

  getPersonasHttp(): Observable<Persona[]>{
    return this.http.get<Persona[]>(this.apiUrl);
    error:(err: any) => console.error(err)
    
  }

  crearPersona(persona: Persona): Observable<Persona>{
    return this.http.post<Persona>(this.apiUrl, persona);
    error:(err: any) => console.error(err)
  }

  actualizarPersona(id: number, persona: Persona): Observable<Persona>{
    return this.http.put<Persona>(`${this.apiUrl}/${id}`, persona);
    error:(err: any) => console.error(err)
  }

  eliminarPersona(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
    error:(err: any) => console.error(err)
  }


}
