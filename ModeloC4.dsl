 workspace "Gestión de Pedidos e Inventario - Tecnología de Williams" {
    description "Sistema para gestión de pedidos mayoristas e inventario"

    model {
        // Personas
        pClienteMayorista = person "Cliente Mayorista"
        pAdministrativo = person "Personal Administrativo"
        pBodeguero = person "Bodeguero"

        // Sistema externo (TICAR/Serbia Entrega como proveedor de logística)
        sLogistica = softwareSystem "Sistema de Logística" {
            tags "Externo"
        }

        // Sistema Principal
        sGestionPedidos = softwareSystem "Sistema de Gestión de Pedidos e Inventario" {
            tags "SistemaGestion"

            // Aplicación Cliente
            portalCliente = container "Portal de Cliente Mayorista" {
                tags "AppWeb"
                pClienteMayorista -> this "Realiza pedidos"
            }

            // Aplicación de administración
            portalAdmin = container "Panel Administrativo" {
                tags "AppWeb"
                pAdministrativo -> this "Gestiona pedidos e inventario"
                pBodeguero -> this "Actualiza estado del inventario"
            }

            // API central
            api = container "API de Pedidos e Inventario" {
                tags "API"
                portalAdmin -> this "CRUD pedidos y stock"
                portalCliente -> this "Consulta disponibilidad / Crear pedidos"
                this -> sLogistica "Envía órdenes de entrega"
                
                // Componentes internos
                compPedido = component "Controlador de pedidos" "Gestiona la creación, edición y consulta de pedidos"
                compInventario = component "Gestor de inventario" "Monitorea el stock disponible y actualiza cantidades"
                compNotificaciones = component "Módulo de notificaciones" "Envía confirmaciones por correo o WhatsApp"

                // Flujo interno
                compPedido -> compInventario "Verifica stock"
                compPedido -> compNotificaciones "Envía confirmación"
                compInventario -> compNotificaciones "Notifica reabastecimiento"
            }

            // Base de datos
            basedatos = container "Base de Datos" {
                tags "Database"
                api -> this "Consulta y actualiza"
            }
        }
    }

    views {
        systemContext sGestionPedidos {
            include *
            autolayout lr
        }

        container sGestionPedidos {
            include *
            autolayout lr
        }

        component  api componentes_api { 
            include *
            autolayout lr
        }

        styles {
            element "SistemaGestion" {
                shape Hexagon
                background #4287f5
                color #ffffff
            }

            element "API" {
                shape RoundedBox
                background #00a878
                color #ffffff
            }

            element "Database" {
                shape Cylinder
                background #ffcc00
            }

            element "Externo" {
                shape Person
                 background   #888888
            }
        }

        theme "https://srv-si-001.utpl.edu.ec/REST_PRO_ERP/Recursos/Imagenes/themeAZ_2023.json"
    }
}
