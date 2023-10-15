import { Timestamp } from 'rxjs';
import { Adress } from './adress.model';
import { Role } from './role.model';

export class User {
  id_user?: number;
  name?: string;
  lastname?: string;
  password?: string;
  email?: string;
  birthday?: number;
  phoneNumber?: number;
  wallet?: Float32Array;
  Picture?: string;
  verificationCode?: string;
  enabled?: boolean;
  resetPasswordToken?: string;
  secret?: string;
  bannedd?: string;
  role_id?: number;
  role_name?: string;
  badge?: string;
  adresse?: Adress;
  role?:Role

}
