const saveBtn = document.getElementById("btn-save");
const title = document.getElementById("title");
const author = document.getElementById("author");
const content = document.getElementById("content");

const main = {
    init : function() {
        const _this = this;
        saveBtn.addEventListener("click", (e) => _this.save())
    },
    save : async function() {
        const data = {
            title: title.value,
            author: author.value,
            content: content.value,
        };

        await fetch("/api/v1/posts",
        {
        method: "POST",
        headers: {
            'content-type': 'application/json; charset=utf-8',
            },
        body: JSON.stringify(data),
        })
        .then((data)=> {
        alert("글이 등록되었습니다.");
        window.location.href ='/';})
        .catch((error) => alert(JSON.stringify(error)))
    }
}


main.init();