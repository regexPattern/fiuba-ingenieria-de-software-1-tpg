"use client";

import IconoEliminar from "./iconoEliminar";
import IconoModificar from "./iconoModificar";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { eliminarCargaHoras } from "./action";
import { obtenerColoresDeProyecto } from "./coloresProyectos";

export default function BloqueCargaDeHoras({
  id,
  tareaNombre,
  cantidadHoras,
  proyectoId
}: {
  id: string;
  tareaNombre: string;
  cantidadHoras: number;
  proyectoId: string;
}) {
  const router = useRouter();

  const alturaRem = 6.0 + Math.min(cantidadHoras - 1, 23) * 0.75;

  const coloresBloque = obtenerColoresDeProyecto(proyectoId);

  async function handleEliminarCarga() {
    const confirmar = confirm(
      "¿Estás seguro de que deseas eliminar esta carga?"
    );
    if (!confirmar) return;

    try {
      const resultado = await eliminarCargaHoras(id);

      if (resultado.exito) {
        alert(resultado.mensaje);
        router.refresh();
      } else {
        console.error("Error al eliminar la carga:", resultado.mensaje);
        alert(`Error: ${resultado.mensaje}`);
      }
    } catch (e) {
      console.error("Error inesperado al eliminar la carga:", e);
      alert(`Error inesperado: ${(e as Error).message}`);
    }
  }

  return (
    <div
      style={{ height: `${alturaRem}rem`, ...coloresBloque }}
      className="border p-2 overflow-y-auto overflow-x-hidden flex flex-col"
    >
      <span className="font-semibold underline">
        {cantidadHoras} hora{cantidadHoras > 1 && "s"}
      </span>
      <span className="text-sm">{tareaNombre}</span>
      <div className="flex justify-space-around mx-auto">
        <Link href={`/carga-de-horas/${id}`}>
          <IconoModificar></IconoModificar>
        </Link>
        <IconoEliminar onEliminar={() => handleEliminarCarga()}></IconoEliminar>
      </div>
    </div>
  );
}
