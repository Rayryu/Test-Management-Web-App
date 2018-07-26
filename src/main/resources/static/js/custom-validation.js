$('.ui.form1').form({
    fields: {
    	
        description: {
            identifier: 'description',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer une description '
            }]
        },
        titre2: {
            identifier: 'titre',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer un titre '
            }]
        },
        titre: {
            identifier: 'nom',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer un titre '
            }]
        },
        typeTest: {
            identifier: 'typeTest',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer un type '
            }]
        },
        precondition: {
            identifier: 'precondition',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer une precondition ou écrire "Aucune" '
            }]
        },

        resultatAttendu: {
            identifier: 'resultatAttendu',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer un résultat attendu '
            }]
        },
        resultatActuel: {
            identifier: 'resultatActuel',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer le résultat actuel '
            }]
        },
    }
});
