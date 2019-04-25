
function confirmDelete() {
    return confirm("Delete Book?");
}

function f(){

    let title = 'AddedBookTitle=' + document.getElementById("titleID").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorID").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnID").value;
    let ref = title+author+isbn;
    return  window.location.href = '' + ref;
}