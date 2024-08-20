async function loadCustomer(){

    if(isNew()){
        return;
    }

    let id = getCustomerId();
    let customer = await getCustomerById(id);
    console.log(customer)


    document.getElementById('textFirstName').value = customer.firstName;
    document.getElementById('textLastName').value = customer.lastName
    document.getElementById('textPhone').value = customer.phone
    document.getElementById('textEmail').value = customer.email

}


function getCustomerId(){
    let auxSplit = window.location.href.split('id=');
    let id = auxSplit[1];

    return id;
}


function isNew(){
    let hasIdInUrl = window.location.href.includes('id=');
    return !hasIdInUrl;
}


async function getCustomerById(id){

    let url = URL_SERVER + 'customer/'+id;
    let response = await fetch(url);
    let json = await response.json();

    return json;
    
}

function clickCreate(){

    let firstName = document.getElementById('textFirstName').value;
    let lastName = document.getElementById('textLastName').value;
    let phone = document.getElementById('textPhone').value;
    let email = document.getElementById('textEmail').value;

    let customer = {
                    "firstName": firstName,
                    "lastName": lastName,
                    "phone": phone,
                    "email": email
                }
    
                save(customer);
}


async function save(customer){


    let url = URL_SERVER;
    let methodType = isNew()?'POST':'PUT';

    if(!isNew){
        console.log("es nuevo");
        url += 'addCustomer'
    }else{
        console.log("no es nuevo");
        url += 'updateCustomer/' + getCustomerId();
    }
    let config = {
        "method": methodType,
        "body": JSON.stringify(customer),
        "headers": {
            'Content-Type': 'application/json'
        }
    }


    await fetch(url, config);
    alert('Customer saved');
    window.location.href = 'customers.html'

}

loadCustomer();
