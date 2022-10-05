import React from "react";
import "./SidebarOption.css";

/**
 * The SidebarOption component to populate the sidebar component
 * 
 * @param {string} text - The text displayed on each option
 * @param {icon} Icon - The icon used to represent each
 * @param {boolean} active - Determines which page you are currently on
 * @returns A sidebaroption component for use in the sidebar
 */
function SidebarOption({ text, Icon, active }) {
  return (
    <div className={`sidebarOption  ${active && "sidebarOption--active"}`}>
      <Icon />
      <h2>{text}</h2>
    </div>
  );
}

export default SidebarOption;