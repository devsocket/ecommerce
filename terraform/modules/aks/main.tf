resource "azurerm_kubernetes_cluster" "this" {
  name                = var.cluster_name
  location            = var.location
  resource_group_name = var.resource_group_name
  dns_prefix          = "${var.cluster_name}-dns"

  default_node_pool {
    name       = "default"
    node_count = 1
    vm_size    = "Standard_B2ms"
    temporary_name_for_rotation = "default-temp"
  }

  identity {
    type = "SystemAssigned"
  }

  tags = var.tags
}


# GHCR image pull secret
resource "kubernetes_secret" "ghcr_pull" {
  metadata {
    name      = "ghcr-secret"
    namespace = "default"
  }

  type = "kubernetes.io/dockerconfigjson"

  data = {
    ".dockerconfigjson" = base64encode(jsonencode({
      auths = {
        "ghcr.io" = {
          username = var.ghcr_username
          password = var.ghcr_pat
          email    = "you@example.com"
        }
      }
    }))
  }
}