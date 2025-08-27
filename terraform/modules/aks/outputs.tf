output "cluster_name" {
  description = "The name of the AKS cluster"
  value       = azurerm_kubernetes_cluster.this.name
}

output "kube_config" {
  description = "The kube config of the AKS cluster"
  value       = azurerm_kubernetes_cluster.this.kube_config_raw
}