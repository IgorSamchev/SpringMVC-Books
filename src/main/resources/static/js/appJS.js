function confirmDelete() {
    return confirm("Delete Book?");
}

function addNewBook() {

    let title = 'AddedBookTitle=' + document.getElementById("titleID").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorID").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnID").value;
    let ref = title + author + isbn;

    if (title.length > 17 && title.length < 46) {
        document.getElementById('titleError').innerText = "";
        if (author.length > 18 && author.length < 37) {
            document.getElementById('authorError').innerText = "";
            if (isbn.length > 16 && isbn.length < 29) {
                document.getElementById('isbnError').innerText = "";
                return window.location.href = '' + ref;
            } else {
                document.getElementById('isbnError').innerText
                    = "ISBN should be a minimum of 3 and maximum of 30 characters";
            }
        } else {
            document.getElementById('authorError').innerText
                = "Author should be a minimum of 3 and maximum of 20 characters";
        }
    } else {
        document.getElementById('titleError').innerText
            = "Title should be a minimum of 3 and maximum of 13 characters";
    }

    // alert(title.length + ' ' + author.length + ' ' + isbn.length);
    // if (title.length > 17 && title.length < 46
    //     && author.length > 18 && author.length < 37
    //     && isbn.length > 16 && isbn.length < 29) {
    //     return window.location.href = '' + ref;
    // } else {
    //     document.getElementById('titleError').innerText = "error";
    // }


}

function editBook(id) {
    let title = 'AddedBookTitle=' + document.getElementById("titleID").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorID").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnID").value;
    let ref = id + title + author + isbn;
    return document.location.href = "/edit_book/" + ref;
}

function addComment(id) {
    let comment = 'com=' + document.getElementById("comment").value.trim();
    let ref = id + comment;
    return document.location.href = "/add_Comment/" + ref;
}

function MainMenu() {
    return document.location.href = "/books_list";
}

function sorter() {
    $('#myTable').tablesorter();
}

function searcher() {

}
