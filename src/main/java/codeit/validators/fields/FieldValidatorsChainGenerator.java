package codeit.validators.fields;

public class FieldValidatorsChainGenerator {

    public static AbstractFieldValidatorHandler getFieldValidatorsChain() {

        AbstractFieldValidatorHandler addressValidator = AddressValidator.getInstance();
        AbstractFieldValidatorHandler birthDateValidator = BirthDateValidator.getInstance();
        AbstractFieldValidatorHandler currencyValidator = CurrencyValidator.getInstance();
        AbstractFieldValidatorHandler descriptionValidator = DescriptionValidator.getInstance();
        AbstractFieldValidatorHandler emailValidator = EmailValidator.getInstance();
        AbstractFieldValidatorHandler firstLastNameValidator = FirstLastNameValidator.getInstance();
        AbstractFieldValidatorHandler linkValidator = LinkValidator.getInstance();
        AbstractFieldValidatorHandler nameValidator = NameValidator.getInstance();
        AbstractFieldValidatorHandler optionalClientDescriptionValidator = OptionalClientDescriptionValidator.getInstance();
        AbstractFieldValidatorHandler optionalCommentValidator = CommentValidator.getInstance();
        AbstractFieldValidatorHandler optionalContactPersonValidator = OptionalContactPersonValidator.getInstance();
        AbstractFieldValidatorHandler optionalEndDateValidator = EndDateValidator.getInstance();
        AbstractFieldValidatorHandler optionalSpecialisationValidator = OptionalSpecialisationValidator.getInstance();
        AbstractFieldValidatorHandler orderStatusValidator = OrderStatusValidator.getInstance();
        AbstractFieldValidatorHandler passwordValidator = PasswordValidator.getInstance();
        AbstractFieldValidatorHandler phoneValidator = PhoneValidator.getInstance();
        AbstractFieldValidatorHandler projectStatusValidator = ProjectStatusValidator.getInstance();
        AbstractFieldValidatorHandler roleValidator = RoleValidator.getInstance();
        AbstractFieldValidatorHandler startDueDateValidator = DueDateValidator.getInstance();
        AbstractFieldValidatorHandler idValidator = IdValidator.getInstance();
        AbstractFieldValidatorHandler optionalIdValidator = OptionalIdValidator.getInstance();
        AbstractFieldValidatorHandler taskStatusValidator = TaskStatusValidator.getInstance();

        addressValidator.setNextFieldValidator(birthDateValidator);
        birthDateValidator.setNextFieldValidator(currencyValidator);
        currencyValidator.setNextFieldValidator(descriptionValidator);
        descriptionValidator.setNextFieldValidator(emailValidator);
        emailValidator.setNextFieldValidator(firstLastNameValidator);
        firstLastNameValidator.setNextFieldValidator(linkValidator);
        linkValidator.setNextFieldValidator(nameValidator);
        nameValidator.setNextFieldValidator(optionalClientDescriptionValidator);
        optionalClientDescriptionValidator.setNextFieldValidator(optionalCommentValidator);
        optionalCommentValidator.setNextFieldValidator(optionalContactPersonValidator);
        optionalContactPersonValidator.setNextFieldValidator(optionalEndDateValidator);
        optionalEndDateValidator.setNextFieldValidator(optionalSpecialisationValidator);
        optionalSpecialisationValidator.setNextFieldValidator(orderStatusValidator);
        orderStatusValidator.setNextFieldValidator(passwordValidator);
        passwordValidator.setNextFieldValidator(phoneValidator);
        phoneValidator.setNextFieldValidator(projectStatusValidator);
        projectStatusValidator.setNextFieldValidator(roleValidator);
        roleValidator.setNextFieldValidator(startDueDateValidator);
        startDueDateValidator.setNextFieldValidator(idValidator);
        idValidator.setNextFieldValidator(optionalIdValidator);
        optionalIdValidator.setNextFieldValidator(taskStatusValidator);

        return addressValidator;
    }
}
