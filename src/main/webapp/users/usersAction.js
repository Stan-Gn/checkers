function submitWithPartnerUsername(partnerUsername) {
    console.log(partnerUsername);
    document.getElementById("partnerUsername").value = partnerUsername;
    document.getElementById("form").submit();
}

function submit() {
    document.getElementById("form").submit();
}