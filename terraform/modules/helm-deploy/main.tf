provider "helm" {
  kubernetes = {
      host                   = var.kube_config["host"]
      client_certificate     = base64decode(var.kube_config["client_certificate"])
      client_key             = base64decode(var.kube_config["client_key"])
      cluster_ca_certificate = base64decode(var.kube_config["cluster_ca_certificate"])
  }
}

resource "helm_release" "this" {
  name       = var.release_name
  chart      = var.helm_chart_path
  namespace  = var.namespace
  values     = [file(var.values_file)]
  create_namespace = true
  
}