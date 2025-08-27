resource "azurerm_eventhub_namespace" "this" {
  name                = var.event_hub_namespace_name
  location            = var.location
  resource_group_name = var.resource_group_name
  sku                 = "Standard"
  capacity            = 1
}