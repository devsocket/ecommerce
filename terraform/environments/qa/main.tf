terraform {
    required_version = ">= 1.5.0"
    required_providers {
        azurerm = {
        source  = "hashicorp/azurerm"
        version = ">= 3.50.0"
        }
        kubernetes = {
        source  = "hashicorp/kubernetes"
        version = ">= 2.9.0"
        }
        helm = {
        source  = "hashicorp/helm"
        version = ">= 2.20.0"
        }
    }
}

module "network" {
  source              = "../../modules/network"
  resource_group_name = var.resource_group_name
  location            = var.location
  vnet_name           = "ecommerce-qa-vnet"
  aks_subnet_name     = "aks-subnet"
  db_subnet_name      = "db-subnet"
  aks_subnet_prefix   = "10.0.1.0/24"
  db_subnet_prefix    = "10.0.2.0/24"
  tags                = var.tags

}
module "aks" {
  source              = "../../modules/aks"
  resource_group_name = var.resource_group_name
  location            = var.location
  cluster_name        = var.cluster_name
}

module "key_vault" {
  source = "../../modules/keyvault"
  resource_group_name = var.resource_group_name
  location            = var.location
  key_vault_name     = var.key_vault_name
}

module "event_hub" {
  source = "../../modules/eventhub"
  resource_group_name = var.resource_group_name
  location            = var.location
  event_hub_namespace_name = var.event_hub_namespace_name
}

module "postgresql" {
  source = "../../modules/postgres"
  resource_group_name = var.resource_group_name
  location            = var.location
  admin_password = var.db_admin_password
  postgres_name = var.postgres_name
}

module "product-service" {
  source = "../../modules/helm-deploy"
  cluster_name = module.aks.cluster_name
  kube_config = module.aks.kube_config
  namespace = "qa"
  release_name = "product-service"
  helm_chart_path = "../../../helm/product-service"
    values_file = "../../../helm/product-service/Values-qa.yaml"
}

module "order-service" {
  source = "../../modules/helm-deploy"
  cluster_name = module.aks.cluster_name
  kube_config = module.aks.kube_config
  helm_chart_path         = "../../../helm/order-service"
  release_name            = "order-service"
  namespace               = "qa"
  values_file             = "../../../helm/order-service/Values-qa.yaml"
}
module "user-service" {
  source = "../../modules/helm-deploy"
  cluster_name = module.aks.cluster_name
    kube_config = module.aks.kube_config
  helm_chart_path         = "../../../helm/user-service"
  release_name            = "user-service"
  namespace               = "qa"
  values_file             = "../../../helm/user-service/Values-qa.yaml"
}