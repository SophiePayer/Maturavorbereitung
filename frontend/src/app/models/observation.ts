export interface Observation {
    id?: string;
    canonical?: string;
    instantiatesReference?: any;
    observationDefinition?: any;
    basedon?: any[];
    triggeredby?: any[];
  }