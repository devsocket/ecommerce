resource "azurerm_virtual_network" "this" {
    name                = var.vnet_name
    address_space       = var.address_space
    location            = var.location
    resource_group_name = var.resource_group_name
    tags = var.tags
}

resource "azurerm_subnet" "aks" {
    name                 = var.aks_subnet_name
    resource_group_name  = var.resource_group_name
    virtual_network_name = azurerm_virtual_network.this.name
    address_prefixes     = [var.aks_subnet_prefix]
}
resource "azurerm_subnet" "db" {
    name                 = var.db_subnet_name
    resource_group_name  = var.resource_group_name
    virtual_network_name = azurerm_virtual_network.this.name
    address_prefixes     = [var.db_subnet_prefix]
}
resource "azurerm_network_security_group" "aks_nsg" {
    name                = "${var.vnet_name}-aks-nsg"
    location            = var.location
    resource_group_name = var.resource_group_name
    security_rule = [ {
        name                       = "AllowKubeAPI"
        priority                   = 100
        direction                  = "Inbound"
        access                     = "Allow"
        protocol                   = "Tcp"
        source_port_range          = "*"
        destination_port_range     = "443"
        source_address_prefix      = "*"
        destination_address_prefix = "*"
    }
    ]
    tags = var.tags
}

resource "azurerm_subnet_network_security_group_association" "aks_nsg_association" {
    subnet_id                 = azurerm_subnet.aks.id
    network_security_group_id = azurerm_network_security_group.aks_nsg.id
}
