const inputFields = document.querySelectorAll('.inputClass');

for(let i=0;i<inputFields.length;i++){
    const currentInputField = inputFields[i];
    let currentLabel = currentInputField.parentElement.firstElementChild;

    currentInputField.addEventListener('focus', function () {
        currentLabel.classList.add('move-up');
    });

    currentInputField.addEventListener('blur', function () {
        if (currentInputField.value === '') {
            currentLabel.classList.remove('move-up')
        }
    });

}

function submitForm(){
    const form = document.getElementById("usernameForm");
    form.submit();

    for(let i=0;i<inputFields.length;i++){
        inputFields[i].value = "";
    }
}