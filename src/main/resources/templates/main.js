fetch('http://localhost:8080/api/users')
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        refreshTable(data);
    });

function refreshTable(jsonObj) {
    var table = document.getElementById('myTable')
    for (var i = 0; i < jsonObj.length; i++) {
        var rw = `<tr>
                            <td>${jsonObj[i].id}</td>
                            <td>${jsonObj[i].username}</td>
                            <td>${jsonObj[i].lastName}</td>
                            <td>${jsonObj[i].password}</td>
                            <td>${jsonObj[i].age}</td>
                            <td>${jsonObj[i]['roles'][0]['name']}</td>
                       </tr>`;
        table.innerHTML += row;
    }
}