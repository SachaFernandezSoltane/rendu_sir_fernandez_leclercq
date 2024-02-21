window.onload = function () {
    //<editor-fold desc="Changeable Configuration Block">

    // Remplacez l'URL par l'URL de votre propre documentation d'API
    var url = "http://localhost:8090/openapi.json"; // Exemple d'URL, remplacez-la par la vôtre

    // the following lines will be replaced by docker/configurator, when it runs in a docker-container
    window.ui = SwaggerUIBundle({
        url: url,
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
    });

    //</editor-fold>
};
