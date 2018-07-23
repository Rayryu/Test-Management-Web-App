$('.ui.form1').form({
    fields: {
    	
        description: {
            identifier: 'description',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer une description '
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
        etapes: {
            identifier: 'etapes',
            rules: [{
                type: 'empty',
                prompt: 'Veuiller entrer au moins une etape'
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
