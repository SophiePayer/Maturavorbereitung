export type Gender = 'unknown' | 'male' | 'female' | 'other';


export interface Patient {
  id?: string;
  active?: boolean;
  gender?: Gender;
  birthdate?: string;
  telecom?: any[];
  name?: any[];
  deceasedBoolean?: boolean;
  deceasedDateTime?: Date;
  address?: any[];
}