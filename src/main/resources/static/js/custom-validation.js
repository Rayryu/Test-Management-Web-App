$('.ui.form1').form({
    fields: {
        description: {
            identifier: 'description',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer une description pour le projet'
            }]
        },
        titre: {
            identifier: 'nom',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer un titre pour le projet'
            }]
        }
    }
});