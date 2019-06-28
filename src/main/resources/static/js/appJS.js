function editBook(id, language) {
    let title = 'AddedBookTitle=' + document.getElementById("titleID").value;
    let author = 'AddedBookAuthor=' + document.getElementById("authorID").value;
    let isbn = 'AddedBookISBN=' + document.getElementById("isbnID").value;
    let ref = id + title + author + isbn;

    if (title.length > 17 && title.length < 56) {
        document.getElementById('titleError').innerText = "";
        if (author.length > 18 && author.length < 37) {
            document.getElementById('authorError').innerText = "";
            if (isbn.length > 16 && isbn.length < 29) {
                document.getElementById('isbnError').innerText = "";
                return document.location.href = "/edit_book/" + ref;
            } else {
                if (language.includes('en'))
                    document.getElementById('isbnError').innerText
                        = "ISBN should be a minimum of 3 and maximum of 13 characters";
                else if (language === 'ee')
                    document.getElementById('isbnError').innerText
                        = "ISBN peaks olema vähemalt 3 ja maksimaalselt 13 tähemärki";
                else if (language === 'ru')
                    document.getElementById('isbnError').innerText
                        = "Поле ISBN должно быть минимумально 3 и максимально 13 символов";
            }
        } else {
            if (language.includes('en'))
                document.getElementById('authorError').innerText
                    = "Author should be a minimum of 3 and maximum of 20 characters";
            else if (language === 'ee')
                document.getElementById('authorError').innerText
                    = "Autor peaks olema vähemalt 3 ja maksimaalselt 20 tähemärki";
            else if (language === 'ru')
                document.getElementById('authorError').innerText
                    = "Поле Автор должно быть минимумально 3 и максимально 20 символов";
        }
    } else {
        if (language.includes('en'))
            document.getElementById('titleError').innerText
                = "Title should be a minimum of 3 and maximum of 30 characters";
        else if (language === 'ee')
            document.getElementById('titleError').innerText
                = "Pealkiri peaks olema vähemalt 3 ja maksimaalselt 30 tähemärki";
        else if (language === 'ru')
            document.getElementById('titleError').innerText
                = "Поле Наименование должно быть минимумально 3 и максимально 30 символов";
    }
}

function doDelete(id) {
    if (confirm("Delete Book?")) {
        document.location.href = "/books/doDelete/" + id;
    }
}


