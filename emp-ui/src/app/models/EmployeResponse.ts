export interface EmployeResponse {
  idEmp?: number
  nom: string;
  prenom: string;
  cin: string;
  telephone: string;
  salaireBase: number;
  idSuperviseur: number;
  idDep?: number;
  nomDep?: string;
}
