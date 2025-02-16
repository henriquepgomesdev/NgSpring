import { platformDynamicServer } from '@angular/platform-server';
import { ApplicationRef, NgModuleRef } from '@angular/core';
import { AppModule } from './app/app.module';

export default async function bootstrap(): Promise<ApplicationRef> {
  const moduleRef: NgModuleRef<AppModule> = await platformDynamicServer().bootstrapModule(AppModule);
  return moduleRef.injector.get(ApplicationRef); // 🔹 Converte NgModuleRef para ApplicationRef
}
