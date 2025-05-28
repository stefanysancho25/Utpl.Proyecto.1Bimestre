workspace "Plataforma de gestion de titulos v2"  {
    description "Sistema de gestión de titulos"
    
    model {
        pEstudiante = person "Estudiante"
        pSecretaria = person "Secretaria de carrera"
        pPrencista = person "Prencista"
        
        sSenescyt = softwareSystem "Senescyt" {
            tags "Software"
        }
        
        sGestion = softwareSystem "Plataforma de gestion" {
            tags "SistemaGestion"
            
            portalEstudiante = container "Pagina de visualización" {
                tags "AppWeb"
                pEstudiante  -> this "Visualiza el título"
            }
            
            portalAdministracion = container "Pagina de administración" {
                tags "AppWeb"
                pSecretaria -> this "Generación de título"
                pPrencista -> this "Imprime el título"
            }
            
            api = container "API" {
                tags "Api"
                portalAdministracion -> this "Generacion/Impresion"
                portalEstudiante -> this "Consulta"
                this -> sSenescyt "Autorizar"
                
                
                emailComponente  = component "Email-componente" "Envia notificaciones a los estudiantes"
                
                incresoComponente = component "Controlador de ingreso" "Permite el ingreso a los usuarios"
            }
            
            basedatos = container "Base de datos" {
                tags "Database"
                api -> this "Obtener/Crear/Actualizar/Eliminar"
            }
        }
        
    
        
    }
    
    views {
        systemContext sGestion {
            include *
            autolayout lr
        }
        
        container sGestion {
            include *
            autolayout lr
        }
        
        component api "Componentes" {
            include *
            autolayout lr
        }
        
        styles {
            element "SistemaGestion" {
                shape Circle
                background #19b92a
                color #000000
            }
        }
        
        theme "https://srv-si-001.utpl.edu.ec/REST_PRO_ERP/Recursos/Imagenes/themeAZ_2023.json"
    }
}
