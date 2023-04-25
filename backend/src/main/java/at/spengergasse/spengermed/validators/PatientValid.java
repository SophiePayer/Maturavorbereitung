package at.spengergasse.spengermed.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE}) //FIELD bei einem Feld
@Retention(RetentionPolicy.RUNTIME) //Wann ausgeführt? Während RUNTIME
@Constraint(validatedBy = {PatientValidator.class})
@Documented
public @interface PatientValid {

    //Errortext ausgeben
    String message() default "Patient.deceasedBoolean and Patient.decesaedDateTime must not both have a value";


    //----- gehört einfach zur Annotation dazu---------
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    //-------------------------------------------------
}

