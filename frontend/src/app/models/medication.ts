export type StatusCode = 'active' | 'inactive'| 'enteredInError';

export interface Medication{
    id?: String,
    identifier?: any[],
    code?: any,
    status?: StatusCode;
    totalVolume?: any,
    ingredient?: any[],
    batch?: any
}