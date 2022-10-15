const saveBtn = document.getElementById("btn-save");
const updateBtn = document.getElementById("btn-update");
const deleteBtn = document.getElementById("btn-delete");
const title = document.getElementById("title");
const author = document.getElementById("author");
const content = document.getElementById("content");
const id = document.getElementById("id");

const main = {
    init : function() {
        const _this = this;

        if(saveBtn) saveBtn.addEventListener("click", (e) => _this.save());
        if(updateBtn) updateBtn.addEventListener("click", (e) => _this.update());
        if(deleteBtn) deleteBtn.addEventListener("click", (e) => _this.delete());

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
        .then((response)=> {
            if(!response.ok) {
              throw new Error(`${response.status} 에러 발생`)
            }
            alert("글이 등록되었습니다.");
            window.location.href ='/';})
        .catch((error) => alert(error))
    },
    update : async function() {
        const data = {
            title: title.value,
            content: content.value,
        }

        const postId = id.value;

        await fetch("/api/v1/posts/"+postId,
        {
            method: "PUT",
            headers: {
                 'content-type': 'application/json; charset=utf-8',
            },
            body: JSON.stringify(data),
        }).then((data)=> {
            alert("글이 수정되었습니다.");
            window.location.href ='/';
        }).catch((error) => alert(JSON.stringify(error)))
    },
    delete : async function() {
        const postId = id.value;

        await fetch("/api/v1/posts/"+postId,
        {
            method: "DELETE",
            headers: {
                'content-type': 'application/json; charset=utf-8',
            },
        }).then((data) => {
            alert("글이 삭제되었습니다.");
            window.location.href = '/';
        }).catch((error) => alert(JSON.stringify(error)))
    },
}

main.init();