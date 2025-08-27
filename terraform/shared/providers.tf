provider "azurerm" {
  features {}
}

provider "kubernetes" {
  host                   = var.kube_config["host"]
  client_certificate     = base64decode(var.kube_config["client_certificate"])
  client_key             = base64decode(var.kube_config["client_key"])
  cluster_ca_certificate = base64decode(var.kube_config["cluster_ca_certificate"])
}

provider "helm" {
  kubernetes {
    host                   = var.kube_config["host"]
    client_certificate     = base64decode(var.kube_config["client_certificate"])
    client_key             = base64decode(var.kube_config["client_key"])
    cluster_ca_certificate = base64decode(var.kube_config["cluster_ca_certificate"])
  }
}