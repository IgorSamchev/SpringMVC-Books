function confirmDelete() {
    return confirm("Delete Book?");
}

function addNewBook(){

    let title = 'AddedBookTitle=' + document.getElementById("titleID").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorID").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnID").value;
    let ref = title+author+isbn;
    return  window.location.href = '' + ref;
}

function editBook(id){
    let title = 'AddedBookTitle=' + document.getElementById("titleID").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorID").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnID").value;
    let ref = id+title+author+isbn;
    return document.location.href="/edit_book/" + ref;
}

function addComment(id) {
    let comment = 'com=' + document.getElementById("comment").value.trim();
    let ref = id + comment;
    return document.location.href="/add_Comment/" + ref;
}

function MainMenu() {
    return document.location.href="/books_list";
}

function sorter() {
    $('#myTable').tablesorter();
}

function searcher() {

}
