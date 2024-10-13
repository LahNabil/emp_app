import {EmployeResponse} from "./EmployeResponse";

export interface PageResponseEmploye {
  content?: Array<EmployeResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
