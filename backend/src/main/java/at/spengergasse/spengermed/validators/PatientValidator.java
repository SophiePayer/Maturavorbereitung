package at.spengergasse.spengermed.validators;

import at.spengergasse.spengermed.model.CodeableConcept;
import at.spengergasse.spengermed.model.Coding;
import at.spengergasse.spengermed.model.Identifier;
import at.spengergasse.spengermed.model.Patient;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PatientValidator implements ConstraintValidator<PatientValid, Patient> {
    @Override
    public void initialize(PatientValid constraintAnnotation){
    }
    @Override
    public boolean isValid(Patient patient, ConstraintValidatorContext context) {

        List<Identifier> identifiers = patient.getIdentifier();

        if(patient.getDeceasedBoolean() != null && patient.getDeceasedDateTime() != null )
        {
            return false;
        }


        /*for(Identifier identifier: identifiers){
            CodeableConcept cc = identifier.getType();
            List<Coding> codings = cc.getCoding();
            int[] barr = new int[10];

            for(Coding c: codings){
                if(c.getCode().equals("SS")) {
                    String svnr = identifier.getValue();

                    if (svnr != null && svnr.length() == 10) {

                        for (int i=0; i<10;i++){
                            barr[i] = svnr.charAt(i) - 48;
                        }
                        int pruef = (3 * barr[0] + 7 * barr[1] + 9 * barr[2] + 5 * barr[4] + 8 * barr[5] + 4 * barr[6] + 2 * barr[7] + 1 * barr[8] + 6 * barr[9]) % 11;

                        if(barr[3] != pruef){
                            return false;
                        }
                    }
                    else {
                        return false;
                    }
                }
            }
        }*/

        return true;
    }
}

