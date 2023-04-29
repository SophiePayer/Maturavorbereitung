export type Status = 'draft' | 'active' | 'retired' | 'unknown';

export interface EvidenceReport {
  id?: string;
  url?: string;
  status?: Status;
  UsageContext?: any[];
  Identifier?: any[];
  Subject?: any;
}