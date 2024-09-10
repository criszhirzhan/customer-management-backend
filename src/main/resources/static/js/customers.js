function init(){
    renderCustomers();
}

async function getCustomers(){

    let url = URL_SERVER + 'getAllcustomers';

    let config = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.token
        },
    }

    let response = await fetch(url, config);
    let json = await response.json();

    return json;
    
}

function getHtmlRowCustomer(customer){
    return `  <tr>
                                            <td>${customer.id}</td>
                                            <td>${customer.firstName}</td>
                                            <td>${customer.lastName}</td>
                                            <td>j${customer.phone}</td>
                                            <td>${customer.email}</td>
                                            <td>
                                                <a href="#" onClick="onClickEdit(${customer.id})" class="btn btn-success btn-circle btn-sm">
                                                    <i class="fas fa-edit"></i>
                                                </a>

                                                <a href="#" onClick="onClickRemove(${customer.id})" class="btn btn-danger btn-circle btn-sm">
                                                    <i class="fas fa-trash"></i>
                                                </a>

                                            </td>
                                        </tr>`
}


function onClickEdit(id){
    window.location.href ='modify-customer.html?id='+id;
}


async function onClickRemove(id){

    let response = confirm("Do you want remove this customer?");
    if(!response)
        return;

    let url = URL_SERVER + 'removeCustomer/'+id;

    let config = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.token
        },
        
    }

    await fetch(url, config);

    renderCustomers();
}


async function renderCustomers(){
    let customers = await getCustomers();
    let html = '';
    for(let customer of customers){
        html += getHtmlRowCustomer(customer);
    }

    let tbody = document.getElementById('tbody-customers');
    tbody.innerHTML = html;
}


init();