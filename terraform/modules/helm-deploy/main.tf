provider "helm" {
  kubernetes = {
      host                   = var.kube_config["host"]
      client_certificate     = base64decode(var.kube_config["client_certificate"])
      client_key             = base64decode(var.kube_config["client_key"])
      cluster_ca_certificate = base64decode(var.kube_config["cluster_ca_certificate"])
  }
}

resource "helm_release" "this" {
  name             = var.release_name
  chart            = "${path.root}/${var.helm_chart_path}"
  namespace        = var.namespace
  values           = [file("${path.root}/${var.values_file}")]
  create_namespace = true
  depends_on = [var.kube_config]  # or module.aks if passed from root

  # Add these:
  replace          = true              # ensures install if not present
  atomic           = true              # rollback on failure
  cleanup_on_fail  = true              # removes failed release

}