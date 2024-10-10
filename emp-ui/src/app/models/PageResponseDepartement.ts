import {DepartementResponse} from "./DepartementResponse";

export interface PageResponseDepartement {
  content?: Array<DepartementResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
