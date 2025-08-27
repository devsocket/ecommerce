output "vnet_id" {
  description = "The ID of the VNet"
  value       = azurerm_virtual_network.this.id
}

output "aks_subnet_id" {
  description = "The ID of the AKS Subnet"
  value       = azurerm_subnet.aks.id
}

output "db_subnet_id" {
  description = "The ID of the DB Subnet"
  value       = azurerm_subnet.db.id
}