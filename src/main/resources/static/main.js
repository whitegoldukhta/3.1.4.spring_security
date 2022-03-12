//------------------ALL-USERS--------------------------------

function getUsers() {

    fetch("http://localhost:8080/api/admin/getAllUsers")
        .then((res) => res.json())
        .then((data) => {
            let temp = "";
            data.forEach(function (user) {

                temp += `
                <tr>
                <td id="id${user.id}">${user.id}</td>
                <td id="name${user.id}">${user.name}</td> 
                <td id="lastName${user.id}">${user.lastName}</td>
                <td id="email${user.id}">${user.username}</td>
                <td>${user.roles.map(role => role.role.replace('ROLE_', ''))}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#editModal" 
                onclick="fillModal(${user.id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#deleteModal" 
                onclick="fillModal(${user.id})">Delete</button></td>
              </tr>`;
            });
            document.getElementById("usersTable").innerHTML = temp;
        })
}
getUsers()

//------------------fillModals--------------------------------

function fillModal(id) {
    fetch("http://localhost:8080/api/admin/getUserById/" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('id').value = user.id;
            document.getElementById('editName').value = user.name;
            document.getElementById('editLastname').value = user.lastName;
            document.getElementById('editEmail').value = user.username;
            document.getElementById('editPassword').value = user.password;
            document.getElementById('delId').value = user.id;
            document.getElementById('delName').value = user.name;
            document.getElementById('delLastname').value = user.lastName;
            document.getElementById('delEmail').value = user.username;
            document.getElementById('delPassword').value = user.password;
        })
    });
}

//------------------SHOW-User--------------------------------

function showUser() {
    //$("#topNav").css("display", "none");
    const showUserURL = 'http://localhost:8080/api/admin/showUserByUsername';
    fetch(showUserURL)
        .then((res) => res.json())
        .then((user) => {

            let temp = "<tr>";
            temp += `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.username}</td>
                <td>${user.roles.map(role => role.role.replace('ROLE_', ''))}</td>
            `;
            temp += "<tr>";
            document.getElementById("userTable").innerHTML = temp;
        })
}
showUser();

//------------------NEW-USER--------------------------------

document.getElementById("newUserForm")
    .addEventListener("submit", newUserForm);

function newUserForm(e){
    e.preventDefault();

    let name = document.getElementById("addName").value;
    let lastName = document.getElementById("addLastname").value;
    let username = document.getElementById("addEmail").value;
    let password = document.getElementById("addPassword").value;
    let roles = selectRole(Array.from(document.getElementById("addRole").selectedOptions)
        .map(r => r.value));

    fetch("http://localhost:8080/api/admin/create", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            username: username,
            password: password,
            roles: roles
        })
    })
        .then(() => {
            document.getElementById("usersTab").click();
            getUsers();
            document.getElementById("newUserForm").reset();
        })
}

//------------------EDIT--------------------------------

function butEdit() {

    let user = {
        id: document.getElementById('id').value,
        name: document.getElementById('editName').value,
        lastName: document.getElementById('editLastname').value,
        username: document.getElementById('editEmail').value,
        password: document.getElementById('editPassword').value,
        roles: selectRole(Array.from(document.getElementById("editRole").selectedOptions)
            .map(r => r.value))
    }

    fetch("http://localhost:8080/api/admin/update", {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)

    })
    $("#editModal .close").click();
    reTable();
}

//------------------select-ROLE--------------------------------
function selectRole(r) {
    let roles = [];
    if (r.indexOf("USER") >= 0) {
        roles.push({"id": 1});
    }
    if (r.indexOf("ADMIN") >= 0) {
        roles.push({"id": 2});
    }
    return roles;
}

//------------------DELETE--------------------------------

function butDelete() {
    fetch("http://localhost:8080/api/admin/delete/" + document.getElementById('delId').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })

    $("#deleteModal .close").click();
    reTable();
}

//------------------reTable--------------------------------

function reTable() {
    // let table = document.getElementById('usersTable')
    // if (table.rows.length > 1) {
    //     table.deleteRow(1);
    // }
    setTimeout(getUsers, 140)

    //--------------User--------------
    var url = "/api/user"

    fetch(url)
        .then((response) => {
            let res = response.json();
            return res;
        })
        .then((user) => {
            console.log(user);
            let roles = ""
            user.roles.forEach((role)=>{roles = roles + role.role + ' '});
            let tbody = document.getElementById('table_user');
            let tr = document.createElement('tr');
            tr.innerHTML = '<td>' + user.id + '</td>' +
                '<td>' + user.username + '</td>' +
                '<td>' + roles + '</td>' +
                '<td>' + user.name + '</td>'+
                '<td>' + user.lastName + '</td>' +
                '<td>' + user.age + '</td>';
            tbody.appendChild(tr);
        })
}