function onDelete() {
    const url = document.getElementsByTagName('form')[0].action;
    debugger;
    fetch(url, {method: 'DELETE'})
        .then(resp => {
            if (resp.status === 500)
                throw new Error('Bad response');
            alert('Deleted!')
        })
        .catch(() => {
            alert('Error occurred!')
        })
}