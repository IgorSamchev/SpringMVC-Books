function addNewBook() {

    let title = 'AddedBookTitle=' + document.getElementById("titleID").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorID").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnID").value;
    let ref = title + author + isbn;

    if (title.length > 17 && title.length < 56) {
        document.getElementById('titleError').innerText = "";
        if (author.length > 18 && author.length < 37) {
            document.getElementById('authorError').innerText = "";
            if (isbn.length > 16 && isbn.length < 29) {
                document.getElementById('isbnError').innerText = "";
                return window.location.href = '' + ref;
            } else {
                document.getElementById('isbnError').innerText
                    = "ISBN should be a minimum of 3 and maximum of 13 characters";
            }
        } else {
            document.getElementById('authorError').innerText
                = "Author should be a minimum of 3 and maximum of 20 characters";
        }
    } else {
        document.getElementById('titleError').innerText
            = "Title should be a minimum of 3 and maximum of 30 characters";
    }
}

function editBook(id) {
    let title = 'AddedBookTitle=' + document.getElementById("titleEdit").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorEdit").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnEdit").value;
    let ref = id + title + author + isbn;
    if (title.length > 17 && title.length < 56) {
        document.getElementById('titleEditError').innerText = "";
        if (author.length > 18 && author.length < 37) {
            document.getElementById('AuthorEditError').innerText = "";
            if (isbn.length > 16 && isbn.length < 29) {
                document.getElementById('isbnEditError').innerText = "";
                return document.location.href = "/edit_book/" + ref;
            } else {
                document.getElementById('isbnEditError').innerText
                    = "ISBN should be a minimum of 3 and maximum of 13 characters";
            }
        } else {
            document.getElementById('AuthorEditError').innerText
                = "Author should be a minimum of 3 and maximum of 20 characters";
        }
    } else {
        document.getElementById('titleEditError').innerText
            = "Title should be a minimum of 3 and maximum of 30 characters";
    }
}

function addComment(id) {
    let comment = 'com=' + document.getElementById("comment").value.trim();
    let ref = id + comment;
    while (ref.includes(';')
    || ref.includes('#')
    || ref.includes('%')
    || ref.includes('?')
    || ref.includes('/')
    || ref.includes('\\')
    || ref.includes('[')
    || ref.includes('|')
    || ref.includes(']')) {
        ref = ref
            .replace(';', '~sem~')
            .replace('#', '~sharp~')
            .replace('%', '~percent~')
            .replace('?', '~question~')
            .replace('/', '~slash~')
            .replace('\\', '~backSlash~')
            .replace('[', '~leftBracket~')
            .replace(']', '~rightBracket~')
            .replace('|', '~wallSlash~');
    }
    return document.location.href = "/add_Comment/" + ref;
}

/**
 * @return {string}
 */
function MainMenu() {
    return document.location.href = "/books_list";
}

/**
 * @return {string}
 */
function LogMenu() {
    return document.location.href = "/log";
}

function confirmDelete() {
    return confirm("Delete Book?");
}


function doDelete(id) {
    if (confirmDelete()) {
        document.location.href = "/books/doDelete/" + id;
    }
}


