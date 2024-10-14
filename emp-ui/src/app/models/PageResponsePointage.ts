import {PointageResponse} from "./PointageResponse";

export interface PageResponsePointage {
  content?: Array<PointageResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
